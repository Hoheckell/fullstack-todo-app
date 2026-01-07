import { ApplicationConfig } from '@angular/core';
import { provideHttpClient } from '@angular/common/http'; // Importante!
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient() // Registra o serviço HTTP globalmente
  ]
};
