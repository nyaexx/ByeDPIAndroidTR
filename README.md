# Android için ByeDPI

<div style="text-align: center;">
  <img alt="ByeDPI logo" src=".github/images/logo.svg" width="100%" height="200px">
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
ciadpi --proto=http,tls --disorder 1 --tlsrec 1+s
```

Bu komut, çoğu ISS’in DPI kurallarını aşmak için yeterli olacaktır. Ancak, bazı durumlarda ek argümanlar kullanmanız veya mevcut değerleri değiştirmeniz gerekebilir. [ByeDPI](https://github.com/hufrea/byedpi/blob/v0.13/README.md) dökümantasyonu, bu konuda detaylı bilgiler içermektedir. Eğer yine de çalıştıramazsanız, UI editör kısmından ayarları deneyerek farklı kombinasyonlar oluşturabilir veya dökümantasyona göz atarak uygun bir yöntem seçebilirsiniz.

(Türkiye için özel konfigürasyon ayarları ve özel dökümantasyon yakında eklenecektir.)

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

Uygulamanın Derlenmesi İçin Gereksinimler:

  1. JDK 8 veya daha yenisi

  2. Android SDK

  3. Android NDK

  4. CMake 3.22.1 veya daha yenisi

Uygulamayı Derlemek İçin:

  1. Alt modüllerle birlikte depoyu klonlayın:
```plaintext
git clone --recurse-submodules
   ```

  3. Depo kök dizininden derleme scriptini çalıştırın:
```plaintext
./gradlew assembleRelease
   ```

**APK dosyası şu dizinde olacaktır:
app/build/outputs/apk/release/**
