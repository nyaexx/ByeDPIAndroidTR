package io.github.dovecoteescapee.byedpi.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.edit
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.dovecoteescapee.byedpi.adapters.AppItemAdapter
import io.github.dovecoteescapee.byedpi.R
import io.github.dovecoteescapee.byedpi.databinding.FragmentFilterBinding
import io.github.dovecoteescapee.byedpi.utility.getPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VpnAppsFilterFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentFilterBinding
    private lateinit var adapter: AppItemAdapter

    private lateinit var apps: List<AppItemAdapter.Model>
    private lateinit var checked: MutableSet<String>

    private var updateJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater, container, false)

        val activity = requireActivity()

        checked = activity.getPreferences().getStringSet("vpn_filtered_apps", emptySet())!!
            .toMutableSet()
        adapter = AppItemAdapter(
            activity,
            { checked.contains(it.packageName) },
            { app, status ->
                checked.apply {
                    if (status) {
                        add(app.packageName)
                    } else {
                        remove(app.packageName)
                    }
                }
                activity.getPreferences().edit {
                    putStringSet("vpn_filtered_apps", checked.toSet())
                }
            })


        binding.appList.layoutManager = LinearLayoutManager(activity)
        binding.appList.addItemDecoration(
            DividerItemDecoration(
                binding.appList.context,
                RecyclerView.VERTICAL
            )
        )

        binding.appList.adapter = adapter

        updateJob?.cancel()
        updateJob = lifecycleScope.launch {
            apps = withContext(Dispatchers.IO) { collectApps() }
            adapter.setList(apps)

            binding.progressCircular.visibility = View.GONE
            binding.appList.visibility = View.VISIBLE
        }

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    private fun collectApps(): List<AppItemAdapter.Model> {
        val context = requireContext()
        val packageManager = context.packageManager

        return packageManager.getInstalledApplications(0)
            .filter { it.packageName != context.packageName }
            .map {
                AppItemAdapter.Model(
                    label = packageManager.getApplicationLabel(it).toString(),
                    packageName = it.packageName,
                    icon = packageManager.getApplicationIcon(it),
                )
            }
    }

    private fun updateFilter(query: String?) {
        if (!::apps.isInitialized) {
            return
        }

        if (query == null) {
            adapter.setList(apps)
            return
        }

        val lQuery = query.lowercase()
        adapter.setList(apps.filter { it.label.lowercase().contains(lQuery) })
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_filter, menu)

        val searchItem = menu.findItem(R.id.action_search)
        (searchItem.actionView as SearchView).setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                updateFilter(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                updateFilter(query)
                return true
            }
        })

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                updateFilter(null)
                return true
            }
        })
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }
}
