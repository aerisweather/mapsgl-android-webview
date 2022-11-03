# mapsgl-android-webview

MapsGL + WebView for Android allows developers to integrate our [MapsGL](https://www.aerisweather.com/products/mapsgl/) product and features into their Android applications. Via a Webview, a map with weather data is rendered through our [MapsGL Javascript SDK](https://www.aerisweather.com/docs/mapsgl/) using the webview.evaluateJavascript method.   Webview.evaluateJavascript supports native Kotlin and Java code to control your map and its weather content.

You'll first need access to our AerisWeather data and services. If you don't already have an account and active subscription, [sign up for an AerisWeather Developer account](https://www.aerisweather.com/signup/developer/). Upon signing up, a demo application with your client ID and secret keys will be generated which you can use for your account configuration below.

## Getting Started

1. Clone project to your working directory.

   https://github.com/aerisweather/mapsgl-android-webview.git

2. Open project with Android Studio [Chipmunk|2021.2.1 Patch2 or later](https://androidstudio.googleblog.com/2022/08/android-studio-chipmunk-202121-patch-2.html) \
   Android Gradle Plugin Version 7.2.2 \
   Gradle Version 7.5.1.


### Aeris id/secret, Mapbox access token

Including a MapsGL view in your app requires you to first set up your account using `MapsGLAccount` and providing your keys in string.xml file:

```
<resources>
    <string name="aerisapi_client_id">Your-client-id</string>
    <string name="aerisapi_client_secret">Your-client-secret</string>
```

Next, in file app/assets/mapviewAndroid.html, add your mapboxGL public access token as [documented by mapbox](https://docs.mapbox.com/help/getting-started/access-tokens/)

```
 window.addEventListener('load', () => {
        mapboxgl.accessToken = 'Your-mapboxGL-public-access-token';
```

Now, you can use the method configureMap() which contains your account information and any other supported options as [documented by our MapsGL Javascript SDK](https://www.aerisweather.com/docs/mapsgl/reference/map-controller/#configuration):


### Getting Map Information

For map information, such as its current center coordinate, zoom level, whether it contains a particular weather layer, use JSBuilder to call javascript function(s) and WebAppInterface to collect asynchronous response(s).

For example, to get the underlying map's current center coordinate:

```
Kotlin -> Javascript call:     JSBuilder.getCenter()
Javascript -> Kotlin response: WebAppInterface.getCenter(lat:Float, lon:Float)
```

### Events and MapsGLViewDelegate

Many of the applicable [events triggered](https://www.aerisweather.com/docs/mapsgl/reference/map-controller/#events) by the `MapController` instance of our MapsGL Javascript SDK are forwarded to WebAppInterface.

The following response methods are supported: 

```
WebAppInterface.onCtrlLoadStart()
WebAppInterface.onCtrlLoadComplete()
WebAppInterface.onCtrlZoom()
WebAppInterface.onCtrlMove()
WebAppInterface.onCtrlMapClick(feature:String)
WebAppInterface.onCtrlAddSource()
WebAppInterface.onCtrlAddLayer()
WebAppInterface.onCtrlRemoveSource()
WebAppInterface.onCtrlRemoveLayer()
WebAppInterface.onAnimationStart()
WebAppInterface.onAnimationStop()
WebAppInterface.onAnimationPause()
WebAppInterface.onAnimationResume()
WebAppInterface.onAnimationAdvance(position: String, date: String)
```

## Example App

Review the example apps for Kotlin for more in-depth knowledge of how to use many of the features provided. These examples demonstrate how to set up your mapGL including a legend view, and how to implement native controls.

## Customization

If you're looking for even more customization options beyond what's supported by this package, you can clone this repo and update the included `mapviewAndroid.html` file with additional configurations and options supported by our core [MapsGL Javascript SDK](https://www.aerisweather.com/docs/mapsgl/). 

To run mapviewAndroid.html in a browser (without Android), update flag:

```
var WEBVIEW = false; // set 'false' to run this without android
```

Insert your aeris id/secret in Javascript configurMap() method.

```
if(WEBVIEW == false) {
        // web-standalone-testing
            configureMap("aerisapi_client_id",  // use your aerisapi_client_id
            "aerisapi_client_secret",           // use your aerisapi_client_secret
```
        

By default this package uses the Mapsbox JS GL SDK in `mapviewAndroid.html`. However, if you'd rather use a different mapping library, you can change this in your app bundle's `mapviewAndroid.html` by instantiating a different map instance and updating the map controller to one supported and provided by our MapsGL SDK. [Review our SDK documentation](https://www.aerisweather.com/docs/mapsgl/getting-started/) on how to configure its usage for different mapping libraries.

## Support

Feel free to post an issue in this Github repo for any bugs, technical issues or questions you may have related to this package. For sales information regarding our [MapsGL](https://www.aerisweather.com/products/mapsgl/) product and subscriptions, reach out to our [sales team](https://www.aerisweather.com/contact/sales/).
