import { Injectable } from '@angular/core';
import { UserRole } from '../models/enums/user-role';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private roleKey = 'userRole';

  constructor() {
    this.setRole(UserRole.User);
  }

  setRole(role: UserRole) {
    localStorage.setItem(this.roleKey, role);
  }

  isAdmin(): boolean {
      return this.getRole() === UserRole.Admin;
  }

  private getRole(): UserRole | null {
    const storedRole = localStorage.getItem(this.roleKey);
    if (storedRole && (storedRole === UserRole.User || storedRole === UserRole.Admin)) {
        return storedRole as UserRole;
    }
    return null;
  }
}
