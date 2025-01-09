// Action Types
export const USER_LOGIN = "USER_LOGIN";
export const USER_LOGOUT = "USER_LOGOUT";
export const USER_SIGNUP = "USER_SIGNUP";
export const USER_AUTH_ERROR = "USER_AUTH_ERROR";

// Optional: State Types (For TypeScript)
export interface User {
  username: string;
  email: string;
  token: string;
}

export interface AuthState {
  user: User | null;
  isAuthenticated: boolean;
  error: string | null;
}
