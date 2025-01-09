import { Action } from 'redux';
import { USER_LOGIN, USER_LOGOUT, USER_SIGNUP, USER_AUTH_ERROR } from "../types";

interface UserLoginAction extends Action<typeof USER_LOGIN> {
    payload: { username: string; token: string };
}

// Simulate API call for login
export const userLogin = (userData: { username: string; token: string }) => ({
  type: USER_LOGIN,
  payload: userData,
});

// Simulate API call for signing up a new user
export const userSignUp = (userData: { username: string; email: string; token: string }) => ({
  type: USER_SIGNUP,
  payload: userData,
});

// Logout user action
export const userLogout = () => ({
  type: USER_LOGOUT,
});

// Handle authentication errors
export const userAuthError = (errorMessage: string) => ({
  type: USER_AUTH_ERROR,
  payload: errorMessage,
});
