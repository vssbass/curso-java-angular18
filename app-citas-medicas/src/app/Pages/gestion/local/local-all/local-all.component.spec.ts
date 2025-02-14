import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalAllComponent } from './local-all.component';

describe('LocalAllComponent', () => {
  let component: LocalAllComponent;
  let fixture: ComponentFixture<LocalAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LocalAllComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LocalAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
