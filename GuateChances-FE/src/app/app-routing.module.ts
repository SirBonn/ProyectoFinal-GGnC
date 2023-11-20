import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { SignupFormComponent } from './components/signup-form/signup-form.component';
import { PageViewComponent } from './pages/page-view.component';
import { ProfileViewComponent } from './pages/profile-view/profile-view.component';
import { AdminViewComponent } from './pages/admin-view/admin-view.component';
import { EmployerViewComponent } from './pages/employer-view/employer-view.component';
import { SeekerViewComponent } from './pages/seeker-view/seeker-view.component';
import { AdminHomeComponent } from './pages/admin-view/admin-home/admin-home.component';
import { CategoriesMngComponent } from './pages/admin-view/categories-mng/categories-mng.component';
import { FilesMngComponent } from './pages/admin-view/files-mng/files-mng.component';
import { ReportesComponent } from './pages/admin-view/reportes/reportes.component';
import { SeekerHomeComponent } from './pages/seeker-view/seeker-home/seeker-home.component';
import { SeekerAplicationComponent } from './pages/seeker-view/seeker-aplication/seeker-aplication.component';
import { SeekerCategoriesMngComponent } from './pages/seeker-view/seeker-categories-mng/seeker-categories-mng.component';
import { SeekerReportsComponent } from './pages/seeker-view/seeker-reports/seeker-reports.component';
import { EmployersReportsComponent } from './pages/employer-view/employers-reports/employers-reports.component';
import { MyOffersViewComponent } from './pages/employer-view/my-offers-view/my-offers-view.component';
import { MyInterviewsComponent } from './pages/employer-view/my-interviews/my-interviews.component';
import { SeekersSelectionPageComponent } from './pages/employer-view/seekers-selection-page/seekers-selection-page.component';
import { EmployersHomeComponent } from './pages/employer-view/employers-home/employers-home.component';
import { HomeGCComponent } from './pages/home-gc/home-gc.component';
import { EmployersStatsComponent } from './pages/admin-view/reportes/employers-stats/employers-stats.component';
import { PaymentLogComponent } from './pages/admin-view/reportes/payment-log/payment-log.component';
import { GuatechancesHomeComponent } from './pages/home-gc/guatechances-home/guatechances-home.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { SocialComponent } from './pages/social/social.component';
import { authGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: '', title: 'GuateChances', component: HomeGCComponent },
  { path: 'login', title: 'Login', component: LoginFormComponent },
  { path: 'signup', title: 'SignUp', component: SignupFormComponent },

  {
    path: 'view',
    canActivate: [authGuard],
    title: 'PageView',
    component: PageViewComponent,
    children: [
      { path: 'admin',
        title: 'AdminView',
        component: AdminViewComponent,
        children: [
          { path: '', title: 'AdminHome', component: AdminHomeComponent },
          { path: 'categories', title:'AdminCategories', component:CategoriesMngComponent},
          { path: 'files', title: 'AdminFiles', component: FilesMngComponent },
          { path: 'reports', title: 'AdminReportes', component: ReportesComponent, children: [
            { path: 'historial-payments', title: 'PaymentLog', component: PaymentLogComponent },
            { path: 'employers-stats', title: 'EmployersStats', component: EmployersStatsComponent }
          ]},
        ]},
      {
        path: 'employer',
        title: 'EmployerView',
        component: EmployerViewComponent,
        children: [
          { path: '', title: 'EmployerHome', component: EmployersHomeComponent },
          { path: 'offers', title: 'EmployerOffers', component: MyOffersViewComponent },
          { path: 'interviews', title: 'EmployerInterviews', component: MyInterviewsComponent },
          { path: 'seekers-selection', title: 'EmployerSeekers', component: SeekersSelectionPageComponent },
          { path: 'reports', title: 'EmployerReports', component: EmployersReportsComponent },
        ],
      },
      { path: 'seeker',
        title: 'SeekerView',
        component: SeekerViewComponent,
        children: [
          { path: '', title: 'SeekerHome', component: SeekerHomeComponent },
          { path: 'aplication', title: 'SeekerAplication', component: SeekerAplicationComponent },
          { path: 'mis-categorias', title: 'SeekerCategories', component: SeekerCategoriesMngComponent},
          { path: 'reports', title: 'SeekerReports', component: SeekerReportsComponent }
        ] },
        {path: 'profile', title: 'ProfileView', component: ProfileViewComponent}
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
