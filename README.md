# Android için ByeDPI

<div style="text-align: center;">
  <img alt="ByeDPI logo" src=".github/images/logotr.svg" width="100%" height="200px">
</div>

---
Bu uygulama yerel VPN Servisi ile DPI (Derin Paket İnceleme) ve Sansürü Aşmak için tasarlanmıştır. Bir SOCKS5 proxy ByeDPI çalıştırır ve tüm trafiği üzerinden yönlendirir.
Varsayılan proje [ByeDPIAndroid](https://github.com/dovecoteescapee/ByeDPIAndroid/).

## Kurulum

Github sayfamızın [relases](https://github.com/nyaexx/ByeDPIAndroidTR/releases/latest) kısmından indirebilir ve direk android cihazınıza kurabilirsiniz.

## Ayarlar

Bazı engellemeleri aşmak için uygulamanın ayarlarını değiştirmeniz gerekebilir. Farklı yapılandırmalar hakkında daha fazla bilgiyi [ByeDPI](https://github.com/hufrea/byedpi/blob/v0.13/README.md) dökümantasyonunda bulabilirsiniz.

Ayarlar bölümünde, VPN modunun çalışmasını istemediğiniz uygulamaları kara listeye alabilir veya yalnızca belirli uygulamalarda çalışmasını istiyorsanız beyaz liste oluşturabilirsiniz.

Örnek Komut:
Komut satırı editörü üzerinden aşağıdaki parametrelerle çalıştırabilirsiniz:
```plaintext
-Ku -a3 -An -Kt,h -s0 -o1 -d2 -r2+s -Ar -At -f-1 -As -b+700 --tls-sni=www.cloudflare.com --ttl=2 --fake 7 --md5sig
```

Bu komut şuan için ISS lerin DPI kurallarını aşmak için yeterli olabilir, ancak çalışmadığı veya performans düşüşü yaşattığı durumlar gözlemlenebilir bu durumda belli argümanları değiştirmeniz veya değerlerle oynamanız gerekir. [ByeDPI](https://github.com/hufrea/byedpi/blob/v0.13/README.md) dökümantasyonu, bu konuda detaylı bilgiler içermektedir. Eğer yine de çalıştıramazsanız, UI editör kısmından ayarları deneyerek farklı kombinasyonlar oluşturabilir veya dökümantasyona göz atarak uygun bir yöntem seçebilirsiniz.

Türkiye için uygulamanın komut satırı editörü içerisine özel birkaç argüman eklenmiştir. Test edebilirsiniz eğer yetersiz olursa bizimle iletişime geçebilirsiniz discord: **nyaex, shouyuma** 

(Türkiye için özel dökümantasyon yakında eklenecek)

## SSS (Sıkça Sorulan Sorular)

### Uygulama root erişimi gerektiriyor mu?
Hayır. Tüm uygulama özellikleri root olmadan çalışır.

###  Bu bir VPN mi?
Hayır. Uygulama, Android’deki VPN modunu trafiği yönlendirmek için kullanır ve hiçbir şeyi uzaktaki bir sunucuya göndermemektedir. Trafiği şifrelemez ve unutmayın IP adresinizi gizlemez.

### Uygulama hangi verileri toplar?
Hiçbir veri toplamaz. Uygulama, verileri uzak bir sunucuya göndermemektedir. Tüm trafik cihazda işlenir.

### Başka platformlar için mevcut mu?
Benzer projeler mevcut.
- Linux için [Zapret](https://github.com/bol-van/zapret) (Önerilir)
- Windows için [GoodByeDPITurkey](https://github.com/cagritaskn/GoodbyeDPI-Turkey) (Önerilir)
- Her iki platform için [ByeDPI](https://github.com/hufrea/byedpi) kullanabilirsiniz ancak Türkçe topluluk desteği azdır.

### DPI Nedir?
DPI (Derin Paket İnceleme), trafiği analiz etme ve filtreleme teknolojisidir. Sağlayıcılar ve devlet kurumları tarafından, siteleri ve hizmetleri engellemek amacıyla kullanılır.

### ByeDPI'yi AdGuard ile nasıl kullanırım?

  1. ByeDPI’yi proxy modunda çalıştırın.

  2. ByeDPI’yi AdGuard’ın "Uygulama yönetimi" sekmesinde istisnalara ekleyin.

  3. AdGuard ayarlarında proxy’yi belirtin:
    
```plaintext
Proxy türü: SOCKS5
Proxy host: 127.0.0.1
Proxy portu: 1080 (varsayılan)
   ```

## Bağımlılıklar

- [ByeDPI](https://github.com/hufrea/byedpi)
- [hev-socks5-tunnel](https://github.com/heiher/hev-socks5-tunnel)
