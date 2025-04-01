# Android için ByeDPI

<div style="text-align: center;">
  <img alt="ByeDPI logo" src=".github/images/logo.svg" width="100%" height="200px">
</div>

---
Bu uygulama yerel VPN Servisi ile DPI (Derin Paket İnceleme) ve Sansürü Aşmak için tasarlanmıştır. Bir SOCKS5 proxy ByeDPI çalıştırır ve tüm trafiği üzerinden yönlendirir.
Varsayılan proje [ByeDPIAndroid](https://github.com/dovecoteescapee/ByeDPIAndroid/).

## Kurulum

Github sayfamızın [relases](https://github.com/nyaexx/ByeDPIAndroidTR/releases/) kısmından indirebilir ve direk android cihazınıza kurabilirsiniz.

## Ayarlar

Bazı engellemeleri aşmak için ayarları değiştirmeniz gerekebilir. Farklı ayarlar hakkında daha fazla bilgiyi [ByeDPI](https://github.com/hufrea/byedpi/blob/v0.13/README.md) dökümantasyonunda bulabilirsiniz.

(Türkiye için özel konfigürasyon ayarları yakında eklenecektir.)

## SSS (Sıkça Sorulan Sorular)

### Uygulama root erişimi gerektiriyor mu?
Hayır. Tüm uygulama özellikleri root olmadan çalışır.

###  Bu bir VPN mi?
Hayır. Uygulama, Android’deki VPN modunu trafiği yönlendirmek için kullanır ancak hiçbir şeyi uzaktaki bir sunucuya göndermemektedir. Trafiği şifrelemez ve IP adresinizi gizlemez.

### Uygulama hangi verileri toplar?
Hiçbir veri toplamaz. Uygulama, verileri uzak bir sunucuya göndermemektedir. Tüm trafik cihazda işlenir.

### Başka platformlar için mevcut mu?
Benzer projeler mevcut.

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
- [ByeDPIAndroid](https://github.com/dovecoteescapee/ByeDPIAndroid/)
- [hev-socks5-tunnel](https://github.com/heiher/hev-socks5-tunnel)

Uygulamanın Derlenmesi İçin Gereksinimler:

  1. JDK 8 veya daha yenisi

  2. Android SDK

  3. Android NDK

  4. CMake 3.22.1 veya daha yenisi

Uygulamayı Derlemek İçin:

  1. Alt modüllerle birlikte depoyu klonlayın:
    git clone --recurse-submodules

  2. Depo kök dizininden derleme scriptini çalıştırın:
    ./gradlew assembleRelease

**APK dosyası şu dizinde olacaktır:
app/build/outputs/apk/release/**
