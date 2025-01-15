// src/interfaces/JwtPayload.ts
export interface JwtPayload {
    sub: string;          // Subject (username)
    roles: string[];      // Roles of the user
    exp: number;          // Expiration timestamp
    iat?: number;         // Issued at timestamp (optional)
    [key: string]: any;   // To handle any additional claims that may be added
  }
  