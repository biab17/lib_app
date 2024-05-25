import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isLoggedIn = false;

  constructor(private router: Router) {}

  login(email: string, password: string): boolean {
    // Dummy authentication check
    if (email === 'user@example.com' && password === 'password') {
      this.isLoggedIn = true;
      this.router.navigate(['/user']);
      return true;
    }
    return false;
  }

  get loggedIn(): boolean {
    return this.isLoggedIn;
  }
}


