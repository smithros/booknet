import { TestBed } from '@angular/core/testing';

import { RecoverCodeService } from './recover-code.service';

describe('RecoverCodeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RecoverCodeService = TestBed.get(RecoverCodeService);
    expect(service).toBeTruthy();
  });
});
