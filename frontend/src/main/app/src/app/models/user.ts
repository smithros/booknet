export class User {
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

  private _userId: number;

  get userId(): number {
    return this._userId;
  }

  set userId(value: number) {
    this._userId = value;
  }

  private _userName: string;

  get userName(): string {
    return this._userName;
  }

  set userName(value: string) {
    this._userName = value;
  }

  private _userPassword: string;

  get userPassword(): string {
    return this._userPassword;
  }

  set userPassword(value: string) {
    this._userPassword = value;
  }

  private _email: string;

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  private _userRole: string;

  get userRole(): string {
    return this._userRole;
  }

  set userRole(value: string) {
    this._userRole = value;
  }

  private _recoverCode: string;

  get recoverCode(): string {
    return this._recoverCode;
  }

  set recoverCode(value: string) {
    this._recoverCode = value;
  }

  private _verified: boolean;

  get verified(): boolean {
    return this._verified;
  }

  set verified(value: boolean) {
    this._verified = value;
  }

  private _activated: boolean;

  get activated(): boolean {
    return this._activated;
  }

  set activated(value: boolean) {
    this._activated = value;
  }
}
