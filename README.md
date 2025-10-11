Simple login page in compose


ViewModel & Flow Usage


🚀 Login Feature & ViewModel

This project demonstrates a clean Jetpack Compose Login Screen using StateFlow and SharedFlow in the ViewModel.

1️⃣ State Management (StateFlow)

_uiState: MutableStateFlow<LoginUiState> → holds UI state.

Observed by Compose UI using collectAsState().

Includes:

✉️ email

🔒 password

✅ isEmailValid, isPasswordValid

⏳ isLoading

🎯 isLoginSuccessful

💬 message (success/error)

2️⃣ One-Time Events (SharedFlow)

_eventFlow: MutableSharedFlow<LoginEvent> → emits one-time events.

Used for:

⚠️ Invalid login toast/message

🔔 Navigation triggers

3️⃣ Key Functions

onEmailChange(newEmail: String)

Updates email in _uiState

Performs email validation ✅

onPasswordChange(newPassword: String)

Updates password in _uiState

Validates password length ≥ 6 🔑

onLoginClick()

Validates email & password

Emits LoginError via _eventFlow if invalid ⚠️

Simulates async login with delay(1000) ⏳

Updates _uiState with success/failure 🎯

isValidEmail(email: String)

Regex-based email validation ✉️✅

4️⃣ Benefits

🖼️ StateFlow → UI always reflects the latest data.

🔄 SharedFlow → ensures one-time events (like toast) are not repeated.

✅ Clear separation of state vs events.

🧪 Easy to write unit tests for validation and login logic.

5️⃣ Unit Testing

✅ onEmailChange() → valid & invalid email checks

✅ onPasswordChange() → valid & invalid password checks

✅ onLoginClick() → success & failure login scenarios

✅ _eventFlow → emits LoginError for invalid credentials
