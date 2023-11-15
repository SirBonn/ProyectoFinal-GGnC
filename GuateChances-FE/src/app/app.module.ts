import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { SignupFormComponent } from './components/signup-form/signup-form.component';
import { SeekerViewComponent } from './pages/seeker-view/seeker-view.component';
import { EmployerViewComponent } from './pages/employer-view/employer-view.component';
import { AdminViewComponent } from './pages/admin-view/admin-view.component';
import { PageViewComponent } from './pages/page-view.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { FooterComponent } from './components/footer/footer.component';
import { UsersCardComponent } from './components/usersComps/users-card/users-card.component';
import { OfferCardComponent } from './components/offersComps/offer-card/offer-card.component';
import { UserEditComponent } from './components/usersComps/user-edit/user-edit.component';
import { OfferEditComponent } from './components/offersComps/offer-edit/offer-edit.component';
import { RefreshCompDirective } from './directives/refresh-comp.directive';
import { AdminHomeComponent } from './pages/admin-view/admin-home/admin-home.component';
import { CategoriesMngComponent } from './pages/admin-view/categories-mng/categories-mng.component';
import { FilesMngComponent } from './pages/admin-view/files-mng/files-mng.component';
import { ReportesComponent } from './pages/admin-view/reportes/reportes.component';
import { AdminnavComponent } from './pages/admin-view/adminnav/adminnav.component';
import { CategoriesCardComponent } from './components/categoriesComps/categories-card/categories-card.component';
import { SeekerNavComponent } from './pages/seeker-view/seeker-nav/seeker-nav.component';
import { SeekerHomeComponent } from './pages/seeker-view/seeker-home/seeker-home.component';
import { SeekerAplicationComponent } from './pages/seeker-view/seeker-aplication/seeker-aplication.component';
import { SeekerReportsComponent } from './pages/seeker-view/seeker-reports/seeker-reports.component';
import { ProfileViewComponent } from './pages/profile-view/profile-view.component';
import { InfoRequestSeekersComponent } from './components/usersComps/info-request-seekers/info-request-seekers.component';
import { SeekerCategoriesMngComponent } from './pages/seeker-view/seeker-categories-mng/seeker-categories-mng.component';
import { MyCategoryCardComponent } from './components/categoriesComps/my-category-card/my-category-card.component';
import { SelectCategoryCardComponent } from './components/categoriesComps/select-category-card/select-category-card.component';
import { ApplicationCardComponent } from './components/applicationsComps/application-card/application-card.component';
import { OfferSimpleCardComponent } from './components/offersComps/offer-simple-card/offer-simple-card.component';
import { EmployerSimpleCardComponent } from './components/usersComps/employer-simple-card/employer-simple-card.component';
import { OffersSeekerViewComponent } from './components/offersComps/offers-seeker-view/offers-seeker-view.component';
import { OfferApplicationComponent } from './pages/seeker-view/offer-application/offer-application.component';
import { ApplicationFormComponent } from './components/applicationsComps/application-form/application-form.component';
import { InterviewCardViewComponent } from './components/interviewsComps/interview-card-view/interview-card-view.component';
import { MyOffersViewComponent } from './pages/employer-view/my-offers-view/my-offers-view.component';
import { EmployersNavComponent } from './pages/employer-view/employers-nav/employers-nav.component';
import { SeekersSelectionPageComponent } from './pages/employer-view/seekers-selection-page/seekers-selection-page.component';
import { MyInterviewsComponent } from './pages/employer-view/my-interviews/my-interviews.component';
import { EmployersReportsComponent } from './pages/employer-view/employers-reports/employers-reports.component';
import { HomeGCComponent } from './pages/home-gc/home-gc.component';
import { GuatechancesHomeComponent } from './pages/home-gc/guatechances-home/guatechances-home.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { SocialComponent } from './pages/social/social.component';
import { EmployersHomeComponent } from './pages/employer-view/employers-home/employers-home.component';
import { OffersEmployerViewComponent } from './components/offersComps/offers-employer-view/offers-employer-view.component';
import { CreateOfferFormComponent } from './components/offersComps/create-offer-form/create-offer-form.component';
import { InterviewEmployerViewComponent } from './components/interviewsComps/interview-employer-view/interview-employer-view.component';
import { ApplicationSimpleCardComponent } from './components/applicationsComps/application-simple-card/application-simple-card.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    SignupFormComponent,
    SeekerViewComponent,
    EmployerViewComponent,
    AdminViewComponent,
    PageViewComponent,
    NavBarComponent,
    FooterComponent,
    UsersCardComponent,
    OfferCardComponent,
    UserEditComponent,
    OfferEditComponent,
    RefreshCompDirective,
    AdminHomeComponent,
    CategoriesMngComponent,
    FilesMngComponent,
    ReportesComponent,
    AdminnavComponent,
    CategoriesCardComponent,
    SeekerNavComponent,
    SeekerHomeComponent,
    SeekerAplicationComponent,
    SeekerReportsComponent,
    ProfileViewComponent,
    InfoRequestSeekersComponent,
    SeekerCategoriesMngComponent,
    MyCategoryCardComponent,
    SelectCategoryCardComponent,
    ApplicationCardComponent,
    OfferSimpleCardComponent,
    EmployerSimpleCardComponent,
    OffersSeekerViewComponent,
    OfferApplicationComponent,
    ApplicationFormComponent,
    InterviewCardViewComponent,
    MyOffersViewComponent,
    EmployersNavComponent,
    SeekersSelectionPageComponent,
    MyInterviewsComponent,
    EmployersReportsComponent,
    HomeGCComponent,
    GuatechancesHomeComponent,
    NotFoundComponent,
    SocialComponent,
    EmployersHomeComponent,
    OffersEmployerViewComponent,
    CreateOfferFormComponent,
    InterviewEmployerViewComponent,
    ApplicationSimpleCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    CommonModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
