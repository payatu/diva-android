# Attention

I've update the project to the a newer Android SDK version since it seems that the author is not willing to maintain it anymore. Besides I have kept the original README file as much as possible where ever it has a useful information.

<br/>

# DIVA Android
---
## What is DIVA?
DIVA (Damn insecure and vulnerable App) is an App intentionally designed to be insecure. We are releasing the Android version of Diva. We thought it would be a nice way to start the year by contributing something to the security community. The aim of the App is to teach developers/QA/security professionals, flaws that are generally present in the Apps due poor or insecure coding practices. If you are reading this, you want to either learn App pentesting or secure coding and I sincerely hope that DIVA solves your purpose. So, sit back and enjoy the ride.

## Why name it Diva?
No offense to anyone, but I was bored with the name DV* and decided to name it more fancy :)

## Who can use Diva?
The idea originated, from a developer’s perspective. The Android security training for developers becomes slightly boring with lot of theory and not much hands-on. SO, I created DIVA for our Android developer training. Diva gamifies secure development learning. With that said, it is an excellent learning tool for aspiring Android penetration testers and security professionals as it gives an insight into app vulnerabilities including the source code. To sum it up:
* Android App developers
* Android Penetration testers
* Security professionals
* Students

## What is included in Diva?

I tried to put as much vulnerabilities as possible in a short period of time. I am sure I have missed out on some vulnerabilities. Please ping me if you know of a good vulnerability tat can be included in Diva. It covers common vulnerabilities in Android apps ranging from insecure storage, input validation to access control issues. I have also included few vulnerabilities in native code, which makes it more interesting from the perspective of covering both Java and C vulnerabilities.

**Current Challenges include:**

1. Insecure Logging
2. Hardcoding Issues – Part 1
3. Insecure Data Storage – Part 1
4. Insecure Data Storage – Part 2
5. Insecure Data Storage – Part 3
6. Insecure Data Storage – Part 4
7. Input Validation Issues – Part 1
8. Input Validation Issues – Part 2
9. Access Control Issues – Part 1
10. Access Control Issues – Part 2
11. Access Control Issues – Part 3
12. Hardcoding Issues – Part 2  **(Not fixed for the new Android SDK)**
13. Input Validation Issues – Part 3 **(Not fixed for the new Android SDK)**

## Can I contribute?
Yes, you can help by sending us the details of vulnerabilities that we can implement in future versions of Diva. Please send an email to info [at] payatu.com with subject “DIVA Contribution”.

## Where can I get Diva?
* The Source can be downloaded from github - https://github.com/payatu/diva-android
* You can also download the debug version of the app from http://www.payatu.com/damn-insecure-and-vulnerable-app/

## How to compile Diva?
* Download the source
* Open the project in Android Studio
* For Native library - open command line
  * $ cd <Project-dir>/app/src/main/jni
  * $ make (This needs to be done only once, unless you make changes to the native code - in which case run "make clean && make")  
  * This will compile the native library and copy all the compiled versions in directory jniLibs which is required when building the app
* From the menu bar: Build->Make Project  or  Run->Run App

## How to run Diva?
* Compile/download the app
* On your phone settings. Go to security and check Unknown Sources checkbox. This allows you to install apps outside of play store. You don’t need to do this if you are installing the app on an emulator.
* Connect your phone to the computer (make sure USB debugging is enabled on your phone) or run the emulator.
* cd <app-directory>
* adb install <apk-file>
* Start playing.

## Feedback and Bug reports?
We would love to hear from you about your experience with Diva. Please send us an email on info (at) payatu dot com with Subject “DIVA Feedback” or “DIVA BUG” based on what you want to share. Please include the below in your email
1. Android version (and API version if possible)
2. Phone make and model (or Emulator Android/API version if using an emulator)
3. Feedback/Bug details and steps to reproduce.

## Author
Aseem Jakhar
**Website:** http://payatu.com
**Email:** info (at) payatu dot com


