ACE (Accounting Concepts Education) 어플리케이션을 Android Studio에서 실행하는 방법을 안내합니다.  
프로젝트는 Java로 작성되었으며, Android Emulator 또는 실제 Android 기기에서 실행할 수 있습니다.

---

## ● 실행환경  
---

- Android Studio Electric Eel 이상 권장  
- JDK 11 이상  
- 최소 SDK: 21 (Android 5.0)  
- Gradle: 자동 다운로드됨  

---

## 1. 프로젝트 open  
---

- Android Studio를 설치한 후 실행합니다.  
- 화면 좌측 상단 메뉴에서 **[File] > [Open]** 을 클릭합니다.  
  ※ Android Studio 시작 화면이라면, **"Open an Existing Project"** 를 선택해도 동일하게 열 수 있습니다.  
- 압축을 해제한 프로젝트 폴더를 선택한 후, **[OK]** 를 클릭합니다.  
  (프로젝트가 열리면 Gradle Sync가 자동으로 진행될 것입니다.)  
  ○ 만약 Sync 실패 시, 상단의 **[File] > [Sync Project with Gradle Files]** 를 선택하십시오.  

---

## 2. 가상 디바이스 (Emulator) 설정  
---

- Android Studio 상단의 **[Tools] > [Device Manager]** 를 클릭합니다.  
- **[Create Device]** 버튼을 클릭합니다.  
- Phone 항목에서 예시: Pixel 5 선택 → **[Next]** 를 클릭합니다.  
  ○ 다른 기기를 선택해도 무방하나, 호환성과 안정성을 고려해 **Pixel 5 사용을 권장**합니다.  
- 시스템 이미지 선택 (예: **Android API 31 이상**) → 다운로드 및 설치 → **[Next]**  
- **[Finish]** 를 클릭한 후 생성된 가상 디바이스를 실행합니다.  

---

## 3. 어플리케이션 실행 방법  
---

- 좌측 상단의 **[Run]** 버튼(녹색 삼각형)을 클릭합니다.  
- 실행 대상 장치를 선택합니다. (2번 단계에서 선택한 Emulator 선택)  
- 어플리케이션이 설치되고 자동 실행됩니다.  
