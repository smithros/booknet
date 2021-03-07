import { TestBed } from '@angular/core/testing';

import { PswdChangeService } from './pswd-change.service';

describe('PswdChangeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PswdChangeService = TestBed.get(PswdChangeService);
    expect(service).toBeTruthy();
  });
});
