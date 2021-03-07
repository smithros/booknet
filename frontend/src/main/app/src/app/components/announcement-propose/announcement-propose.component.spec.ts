import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnouncementProposeComponent } from './announcement-propose.component';

describe('AnnouncementProposeComponent', () => {
  let component: AnnouncementProposeComponent;
  let fixture: ComponentFixture<AnnouncementProposeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnnouncementProposeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnnouncementProposeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
