import { ApplicationConfig, ErrorHandler, provideZoneChangeDetection } from '@angular/core';
import { provideRouter, TitleStrategy, withComponentInputBinding } from '@angular/router';

import { routes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { AppTitleService } from './service/commons/app-title.service';
import { GlobalErrorService } from './service/commons/global-error.service';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { tokenAccessInterceptor } from './service/security/token-access.interceptor';
import { tokenRefreshInterceptor } from './service/security/token-refresh.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }), 
    provideRouter(routes, withComponentInputBinding()),
    provideAnimationsAsync(),
    {provide: TitleStrategy, useClass: AppTitleService},
    {provide: ErrorHandler, useClass: GlobalErrorService},
    provideHttpClient(withInterceptors([tokenAccessInterceptor, tokenRefreshInterceptor])),
  ]
};
