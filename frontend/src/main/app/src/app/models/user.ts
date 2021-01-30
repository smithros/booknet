export class User {
  private _userId: number;
  private _userName: string;
  private _userPassword: string;
  private _email: string;
  private _userRole: string;
  private _recoverCode: string;
  private _verified: boolean;
  private _activated: boolean;

  constructor(userId: number, userName: string, userPassword: string,
              email: string, userRole: string, recoverCode: string,
              verified: boolean, activated: boolean
  ) {
    this._userId = userId;
    this._userName = userName;
    this._userPassword = userPassword;
    this._email = email;
    this._userRole = userRole;
    this._recoverCode = recoverCode;
    this._verified = verified;
    this._activated = activated;
  }

  get userId(): number {
    return this._userId;
  }

  set userId(value: number) {
    this._userId = value;
  }

  get userName(): string {
    return this._userName;
  }

  set userName(value: string) {
    this._userName = value;
  }

  get userPassword(): string {
    return this._userPassword;
  }

  set userPassword(value: string) {
    this._userPassword = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  get userRole(): string {
    return this._userRole;
  }

  set userRole(value: string) {
    this._userRole = value;
  }

  get recoverCode(): string {
    return this._recoverCode;
  }

  set recoverCode(value: string) {
    this._recoverCode = value;
  }

  get verified(): boolean {
    return this._verified;
  }

  set verified(value: boolean) {
    this._verified = value;
  }

  get activated(): boolean {
    return this._activated;
  }

  set activated(value: boolean) {
    this._activated = value;
  }
}
