import { TestBed } from '@angular/core/testing';

import { TkStorageService } from './tk-storage.service';

describe('TkStorageService', () => {
  let service: TkStorageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TkStorageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
