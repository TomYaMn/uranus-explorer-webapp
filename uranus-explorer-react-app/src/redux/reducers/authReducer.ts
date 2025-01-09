// src/redux/reducers/authReducer.ts
import { USER_LOGIN, USER_LOGOUT, USER_SIGNUP, USER_AUTH_ERROR } from "../types";

const initialState = {
  user: null,
  error: null,
  isAuthenticated: false,
};

const authReducer = (state = initialState, action: any) => {
  switch (action.type) {
    case USER_LOGIN:
      return {
        ...state,
        user: action.payload,
        isAuthenticated: true,
      };
    case USER_LOGOUT:
      return {
        ...state,
        user: null,
        isAuthenticated: false,
      };
    case USER_SIGNUP:
      return {
        ...state,
        user: action.payload,
        isAuthenticated: true,
      };
    case USER_AUTH_ERROR:
      return {
        ...state,
        error: action.payload,
      };
    default:
      return state;
  }
};

export default authReducer;
