import { TestBed } from '@angular/core/testing';

import { MotivoService } from './motivo.service';

describe('MotivoServiceService', () => {
  let service: MotivoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MotivoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
