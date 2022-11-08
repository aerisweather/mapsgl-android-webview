# mapsgl-android-webview

Integrate [MapsGL](https://www.aerisweather.com/products/mapsgl/) products and features into Android applications with MapsGLWebviewlib. In a Webview, a map with weather data is rendered through our [MapsGL Javascript SDK](https://www.aerisweather.com/docs/mapsgl/) using the webview.evaluateJavascript() method.  Native Kotlin environment can send javascript via Webview.evaluateJavascript() to control your map and its weather content.

## Permissions

#### Aeris id and secret

You'll first need access to our AerisWeather data and services. If you don't already have an account and active subscription, [sign up for an AerisWeather Developer account](https://www.aerisweather.com/signup/developer/). Upon signing up, a demo application with your client ID and secret keys will be generated which you can use for your account configuration below.

Including a MapsGLWebview in your app requires you first to set up your account using `MapsGLAccount` and provide your keys in the file MapsGLWebviewlib/src/main/res/values/strings.xml.

```
<resources>
    <string name="aerisapi_client_id">Your-client-id</string>
    <string name="aerisapi_client_secret">Your-client-secret</string>
```
#### Mapbox access token

Next, add your mapboxGL public access token as [documented by mapbox](https://docs.mapbox.com/help/getting-started/access-tokens/) in the file MapsGLWebviewlib/src/main/assets/mapview_android.html.

```
 window.addEventListener('load', () => {
        mapboxgl.accessToken = 'Your-mapboxGL-public-access-token';
```

## Getting Started

1. Clone the project to your working directory.

   https://github.com/aerisweather/mapsgl-android-webview.git

2. Open project with Android Studio [Chipmunk|2021.2.1 Patch2](https://androidstudio.googleblog.com/2022/08/android-studio-chipmunk-202121-patch-2.html) \
   Android Gradle Plugin Version 7.2.2 \
   Gradle Version 7.5.1.
   
3. Update and verify your permissions in the library's strings.xml (Aeris id,secrect) and mapview_android.html (MapBox access token).  Exercising method configureMap() will instantiate a valid Mapcontroller object with supported options as [documented by our MapsGL Javascript SDK](https://www.aerisweather.com/docs/mapsgl/reference/map-controller/#configuration).

## Kotlin-Javascript Interop

Supplied in this example for MapsGL InterOp are four files: \
These classes enable developers with MapsGL to render and interact with maps. \
a. JSBuilder.kt: Kotlin -> Javascript \
b. WebAppInterface.kt: Javascript -> Kotlin \
c. mapview_android.html: Javascript MapsGL interface \
d. MapsGLWebview.kt: Host the above HTML file 

<img width="450" src="https://user-images.githubusercontent.com/116283403/200030693-19f00295-efc3-4ef8-a9ba-b17b0cc1bcd3.png"/>

## Getting Map Information

For map information, use JSBuilder to call javascript function(s) and WebAppInterface to collect asynchronous response(s).
The following map information requests are supported: 
```
JSBuilder.getCenter()
JSBuilder.getZoom()
JSBuilder.getBounds()
JSBuilder.getBearing()
JSBuilder.getPitch()
JSBuilder.getFov()
JSBuilder.hasWeatherLayer(name:String)
JSBuilder.query(lat: Double, lon: Double)
JSBuilder.getStartDate()
JSBuilder.getEndDate()
JSBuilder.getCurrentDate()
```
As an example, to retrieve the map's current center coordinate:

```
Kotlin -> Javascript call:     JSBuilder.getCenter()
Javascript -> Kotlin response: WebAppInterface.getCenter(lat:Float, lon:Float)
```
<img height="400" src="https://user-images.githubusercontent.com/116283403/200594669-4c970cc6-f157-4246-a71b-d4335566abc6.png"/>  <img height="400" src="https://user-images.githubusercontent.com/116283403/200595067-c5a12a62-211e-4b1a-9ab4-302562cd28aa.png"/>

## Events and MapsGLViewDelegate

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

Please review the Kotlin example app for additional features provided. These examples demonstrate how to set up your mapGL including a legend view, and how to implement native-javascript interop.

<img height="400" src="https://user-images.githubusercontent.com/116283403/200595378-564888a5-4d94-412d-b32f-e0f3999ea8ad.png"/>  <img height="400" src="https://user-images.githubusercontent.com/116283403/200595407-91b7e14f-fb80-4de6-b975-daab618bd5af.png"/>

## Customization

If you're looking for even more customization options beyond what's supported by this package, you can clone this repo and update the included `mapview_android.html` file with additional configurations and options supported by our core [MapsGL Javascript SDK](https://www.aerisweather.com/docs/mapsgl/). 

To run mapview_android.html in a browser (without Android), update the flag:

```
var WEBVIEW = false; // set 'false' to run this without android
```

Insert your Aeris id/secret in Javascript configurMap() method.

```
if(WEBVIEW == false) {
        // web-standalone-testing
            configureMap("aerisapi_client_id",  // use your aerisapi_client_id
            "aerisapi_client_secret",           // use your aerisapi_client_secret
```
        

By default, this package uses the Mapbox JS GL SDK in `mapview_android.html`. However, if you'd rather use a different mapping library, you can change this in your app bundle's `mapview_android.html` by instantiating a different map instance and updating the map controller to one provided by our MapsGL SDK. [Review our SDK documentation](https://www.aerisweather.com/docs/mapsgl/getting-started/) on how to configure its usage for different mapping libraries.

## Support

Feel free to post an issue in this Github repo for any bugs, technical issues, or questions you may have related to this package. For sales information regarding our [MapsGL](https://www.aerisweather.com/products/mapsgl/) product and subscriptions, reach out to our [sales team](https://www.aerisweather.com/contact/sales/).
