# mapsgl-android-webview

Integrate [MapsGL](https://www.aerisweather.com/products/mapsgl/) product and features into Android applications with Webview and our example mapviewAndroid.html. In a Webview, a map with weather data is rendered through our [MapsGL Javascript SDK](https://www.aerisweather.com/docs/mapsgl/) using the webview.evaluateJavascript() method.   Native Kotlin environment can send javascript via Webview.evaluateJavascript() to control your map and its weather content.

## Permissions

#### Aeris id and secret

You'll first need access to our AerisWeather data and services. If you don't already have an account and active subscription, [sign up for an AerisWeather Developer account](https://www.aerisweather.com/signup/developer/). Upon signing up, a demo application with your client ID and secret keys will be generated which you can use for your account configuration below.

Including a MapsGL view in your app requires you to first set up your account using `MapsGLAccount` and providing your keys in strings.xml file.

```
<resources>
    <string name="aerisapi_client_id">Your-client-id</string>
    <string name="aerisapi_client_secret">Your-client-secret</string>
```
#### Mapbox access token

Next, in file app/assets/mapviewAndroid.html, add your mapboxGL public access token as [documented by mapbox](https://docs.mapbox.com/help/getting-started/access-tokens/).

```
 window.addEventListener('load', () => {
        mapboxgl.accessToken = 'Your-mapboxGL-public-access-token';
```

## Getting Started

1. Clone project to your working directory.

   https://github.com/aerisweather/mapsgl-android-webview.git

2. Open project with Android Studio [Chipmunk|2021.2.1 Patch2](https://androidstudio.googleblog.com/2022/08/android-studio-chipmunk-202121-patch-2.html) \
   Android Gradle Plugin Version 7.2.2 \
   Gradle Version 7.5.1.
   
3. Update and verify your permissions in strings.xml (Aeris id,secrect) and mapviewAndroid.html (MapBox access token).  Exercising method configureMap() will instantiate a valid Mapcontroller object with supported options as [documented by our MapsGL Javascript SDK](https://www.aerisweather.com/docs/mapsgl/reference/map-controller/#configuration).

## Kotlin-Javascript Interop

Supplied in this example app for MapsGL InterOp are three files: \
These classes enable developer with MapsGL to render and interact with maps. \
a. JSBuilder.kt: Kotlin -> Javascript \
b. WebAppInterface.kt: Javascript -> Kotlin \
c. mapviewAndroid.html: Javascript MapsGL interface \
<img width="400" src="https://user-images.githubusercontent.com/116283403/199997255-778efb6b-8de5-465a-8230-ab1377f62e51.png"/>

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
As an example, to retrieve map's current center coordinate:

```
Kotlin -> Javascript call:     JSBuilder.getCenter()
Javascript -> Kotlin response: WebAppInterface.getCenter(lat:Float, lon:Float)
```
<img height="400" src="https://user-images.githubusercontent.com/116283403/199754935-a2f0d303-c794-4f77-bb96-48cd3557cb01.png"/>  <img height="400" src="https://user-images.githubusercontent.com/116283403/199754088-10cd354f-464f-44f6-8ad9-d5df6dbe6f13.png"/>

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

<img height="400" src="https://user-images.githubusercontent.com/116283403/199754113-a18d1497-e339-4d9e-b58d-5e95321ba65e.png"/>  <img height="400" src="https://user-images.githubusercontent.com/116283403/199745710-a44c19bf-38ff-4c75-9918-8f060842d7d5.png"/>

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
        

By default this package uses the Mapsbox JS GL SDK in `mapviewAndroid.html`. However, if you'd rather use a different mapping library, you can change this in your app bundle's `mapviewAndroid.html` by instantiating a different map instance and updating the map controller to one provided from our MapsGL SDK. [Review our SDK documentation](https://www.aerisweather.com/docs/mapsgl/getting-started/) on how to configure its usage for different mapping libraries.

## Support

Feel free to post an issue in this Github repo for any bugs, technical issues or questions you may have related to this package. For sales information regarding our [MapsGL](https://www.aerisweather.com/products/mapsgl/) product and subscriptions, reach out to our [sales team](https://www.aerisweather.com/contact/sales/).
