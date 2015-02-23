footer: Intro to Android CodeLab, by Etienne Caron, Feb. 2015
slidenumbers: true

# CodeLab README.md

The material within this repo first originated as prep material for a 'part 2' codelab I am doing for the Computer Science student association.

This file is MarkDown with an eye to be a DeckSet compatible slide-show. 

From the FAQs heard during McHacks hackathon:

- Theming 
	- Toolbar
- List / Detail view
	- List view
	- Handlers
	- Intent to go from one Activity to another
		- (Quick note on Photo taking)
	- Activities and preserving state
- Using APIs 
	- Retrofit and REST
	- API usage 
		- Pocket ?
		- GitHub
- Applied Fragments
	- Lifecycle
	- List/Detail tablet example
	- Google maps API usage

I'll be using Git-commits to separate each code-lab steps in GitHub.

---

# Codelab Goals

We'll try to cover:

- Theming 
- Handling events - Button clicks
- List / Detail view
- Using APIs (Retrofit and Github)
- Fragments

--- 

## 1. Theming and retro-compatible Material design  

This segment was inspired by [this Android-Dev blog post](http://android-developers.blogspot.ca/2014/10/appcompat-v21-material-design-for-pre.html) and this [other post](https://chris.banes.me/2014/10/17/appcompat-v21/) that show how to do core Material Design / theming for pre-Lollipop devices.

---

### `colors.xml`

^ Let's first build ourselves a color scheme. This involves creating a color definition file, `colors.xml` in the `app/src/main/res/values` folder.

^ Point out the different types of project views in Android Studio. 

```xml
<resources>
    <color name="my_awesome_color">#F00</color>
    <color name="my_awesome_darker_color">#A00</color>
    <color name="accent">#fe0</color>
    <color name="blue_selected">#33b5e5</color>
    <color name="gray">#d3d3d3</color>
    <color name="transparent">#00000000</color>
</resources>
```

---

### `themes.xml`

^ This is where we can first use our color definitions. Themes like these can be applied at various levels. (See next slide)

```xml
<resources>
    <style name="Theme.CodeLabTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- colorPrimary is used for the default action bar background -->
        <item name="colorPrimary">@color/my_awesome_color</item>

        <!-- colorPrimaryDark is used for the status bar -->
        <item name="colorPrimaryDark">@color/my_awesome_darker_color</item>

        <!-- colorAccent is used as the default value for colorControlActivated,
             which is used to tint widgets -->
        <item name="colorAccent">@color/accent</item>

        <!-- You can also set colorControlNormal, colorControlActivated
             colorControlHighlight, and colorSwitchThumbNormal. -->

    </style>
</resources>
```

---

### `AndroidManifest.xml`

^ Note here how we apply it to the whole of the Application.

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.kanawish.codelab" >
    <application
        android:allowBackup="true"
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/Theme.CodeLabTheme" >
        <activity
            android:name=".MainActivity"
            android:label="@string/app_name" >
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

---

### Defining Toolbar in `activity_main.xml`

^ Jumping to the layout for our main Activity, here we show how to use the new (retro-compatible) Toolbar widget introduced with Lollipop.

^ Also note how we used `"?attr/colorPrimary` to color the Toolbar, chaining `colors.xml` -> `themes.xml` -> `AndroidManifest.xml`. 

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:tools="http://schemas.android.com/tools"
                xmlns:app="http://schemas.android.com/apk/res-auto"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                tools:context=".MainActivity"
                android:orientation="vertical">
	<!-- ... -->
    <android.support.v7.widget.Toolbar
        android:id="@+id/my_awesome_toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:minHeight="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        />
	<!-- ... -->
</LinearLayout>
```

---

### Assigning Toolbar in `MainActivity.java`
 
^ This is the last step to tie everything together.

^ Point out the use of `findViewById()`, casting views... we'll be seeing how to register click listeners later, this is important.

^ There is more here to explore, on theming the pop-up menu, the text color, etc. Left as an exercise for the reader.

```java
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Assigning the toolbar to "ActionBar duty".
		Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
		setSupportActionBar(toolbar);
	}
```

---

## 2 Handling events



---

## 3 ListView and Detail Activities

---

## 4 Using APIs (Retrofit and GitHub)

---

## 5 Fragments

---

