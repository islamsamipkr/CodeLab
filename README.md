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

# on GitHub: 

[https://github.com/kanawish/CodeLab.git](https://github.com/kanawish/CodeLab.git)

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

A quick look at the basics of handling events, layouts, and using logcat.

---

### `activity_main.xml`

Let's add a button to our layout.

^ The text definition here leaves to be desired, give a quick demo on `strings.xml` usage time permitting.

```xml
        <LinearLayout
            android:orientation="vertical"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent">
            <Button
                android:id="@+id/simpleButton"
                android:text="Click me"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"/>
            <TextView
                android:text="@string/hello_world"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"/>
        </LinearLayout>
```

---

### `MainActivity.java`

^ Button is found in layout, cast to proper type, then we assign a click listener.

^ Point out how Logs are used, the idea behind TAG constant, and show a live log of the button being clicked. 

^ Demo the in-IDE logs for simplicity, could touch on pidcat to show off colors.

^ Make sure everybody has grasped 1 + 2 before moving on to 3.

```java
	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Assigning the toolbar to "ActionBar duty".
		Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
		setSupportActionBar(toolbar);

		Button button = (Button) findViewById(R.id.simpleButton);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "simpleButton click listener was called.");
			}
		});
	}
```

---

## 3 ListView and Detail Activities


---

### `activity_main.xml` - Add the list view to your UI layout.

```xml
       <LinearLayout
            android:orientation="vertical"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent">

			<!-- ... -->
			
          <ListView
                android:id="@+id/userListView"
                android:layout_width="fill_parent"
                android:layout_height="fill_parent"
                android:background="?attr/colorAccent"
                />
                
       </LinearLayout>
```

---

### `User.java`

```java
public class User {

	String firstName;
	String lastName;

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
```

---

### `User.java`, continued

```java
...
	public static List<User> buildMockUserList() {
		ArrayList<User> users = new ArrayList<>();

		users.add(new User("Alice", "Alguard"));
		users.add(new User("Bob", "Barker"));
		users.add(new User("Charles", "Colander"));
		users.add(new User("Damien", "Delany"));

		return users;
	}
...
```

---

### `UsersAdapter.java`

```java
class UsersAdapter extends ArrayAdapter<User> {
	// ...
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get the data item for this position
		User user = getItem(position);

		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
		}

		// Lookup view for data population
		TextView firstName = (TextView) convertView.findViewById(android.R.id.text1);
		TextView lastName = (TextView) convertView.findViewById(android.R.id.text2);

		// Populate the data into the template view using the data object
		firstName.setText(user.getFirstName());
		lastName.setText(user.getLastName());

		// Return the completed view to render on screen
		return convertView;
	}
}
```

---

### `MainActivity.java onCreate()`

```java
		// List View + Adapter
		ListView listView = (ListView)findViewById(R.id.userListView);
		final UsersAdapter usersAdapter = new UsersAdapter(this, User.buildMockUserList());
		listView.setAdapter(usersAdapter);

		Button button = (Button) findViewById(R.id.simpleButton);
		button.setOnClickListener(new View.OnClickListener() {
			int counter = 0;
			@Override
			public void onClick(View v) {
				Log.d(TAG, "simpleButton click listener was called.");
				usersAdapter.add(new User("Johnny", "Number "+ ++counter));
			}
		});

		// Quick and dirty example of handling clicks
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				User user = usersAdapter.getItem(position);
				Log.d(TAG, "User clicked on " + user.getFirstName());
			}
		});
```

---

## 4 Using APIs (Retrofit and GitHub)

---

## 5 Fragments

---

