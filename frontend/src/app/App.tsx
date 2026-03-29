import * as React from 'react';
import { createRoot } from 'react-dom/client'; // <-- Importe o "Pincel" do React
import { RouterProvider } from 'react-router';
import { router } from './routes';
import '../styles/index.css';

export default function App() {
  return <RouterProvider router={router} />;
}

// A PONTE MÁGICA:
// Isso aqui pega o seu componente App e cola no <div id="root"> do seu HTML
const container = document.getElementById('root');
if (container) {
  const root = createRoot(container);
  root.render(
      <React.StrictMode>
        <App />
      </React.StrictMode>
  );
}