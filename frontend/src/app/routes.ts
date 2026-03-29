import { createBrowserRouter } from 'react-router';
import { Login } from './pages/Login';
import { Painel } from './pages/Painel';

export const router = createBrowserRouter([
  {
    path: "/",
    Component: Login,
  },
  {
    path: "/app",
    Component: Painel,
  },
]);
