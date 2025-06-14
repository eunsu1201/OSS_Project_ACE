ACE (Accounting Concepts Education) 어플리케이션을 Android Studio에서 실행하는 방법을 안내합니다.  
프로젝트는 Java로 작성되었으며, Android Emulator 또는 실제 Android 기기에서 실행할 수 있습니다.

---

## ● 실행환경  
---

- Android Studio Electric Eel 이상 권장  
- JDK : 21 (Android Studio 설치 시 내장 되어 있는 JDK 버전임. 21 버전을 사용하길 권장합니다.)
- SDK : 21 이상 (Android 5.0)  
- Gradle : 8.11.1 

---

## 1. 프로젝트 open  
---

- Android Studio를 설치한 후 실행합니다.  
- 시작화면에서 **[Open]** 을 클릭한 후 압축을 해제한 Application_ACE 프로젝트 폴더를 선택하여 open 합니다.
  ○ 만약 Sync 실패 시, 상단의 **[File] > [Sync Project with Gradle Files]** 를 선택하십시오.  
- 중요) local.properties에서 경로를 실행하는 장치의 경로에 맞게 수정 한 후 다시 빌드를 합니다.
  (수정하지 않으면 오류가 발생하며 실행되지 않으니 반드시 수정해야 합니다.)
  ==> sdk.dir=C\:\\Users\\es\\AppData\\Local\\Android\\Sdk (이 코드를 수정하면 됩니다.)

---

## 2. 가상 디바이스 (Emulator) 설정  
---

- Android Studio 상단의 **[Tools] > [Device Manager]** 를 클릭합니다.  
- 좌측 상단의 **[+] > [Create Device]** 를 클릭합니다.  
- Phone 항목에서 예시: Pixel 5 선택 → **[Next]** 를 클릭합니다.  
  ○ 다른 기기를 선택해도 무방하나, 호환성과 안정성을 고려해 **Pixel 5 사용을 권장**합니다.  
- 시스템 이미지 선택 (예: **Android API 31 이상**) → 다운로드 및 설치 → **[Next]**  
- **[Finish]** 를 클릭한 후 생성된 가상 디바이스를 실행합니다.  

---

## 3. 어플리케이션 실행 방법  
---

- 메뉴 상단의 **[Run]** 버튼(녹색 삼각형)을 클릭합니다.  
- 실행 대상 장치를 선택합니다. (2번 단계에서 선택한 Emulator 선택)  
- 어플리케이션이 설치되고 실행됩니다.  
