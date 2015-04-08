# NavDrawerTemplate
:rocket: Navigation Drawer Template


## Demo
![navdrawer](resources/navdrawer.gif)


## Usage
### Clone and remove `.git`

```sh
$ clone git clone https://github.com/importre/NavDrawerTemplate <YOUR_PROJECT_NAME>
$ cd <YOUR_PROJECT_NAME>
$ rm -rf .git
```

### Change package name
- mkdir `app/src/(main|androidTest)/java/<YOUR_PACKAGE_NAME>`
- move below files to your package in Android Studio
    - `BaseActivity.java`
    - `BaseFragment.java`
    - `MainActivity.java`
    - `MainFragment.java`
    - `ApplicationTest.java`
- and fix `import` package name of `R`
- replace with your package name in `AndroidManifest.xml`, `app/build.gradle`
- change `app_name` in `strings.xml`
- edit `README.md`
- fix problems if there are any bugs
