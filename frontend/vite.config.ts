import { defineConfig } from 'vite'
// Mudei aqui para o TypeScript parar de chorar com o erro TS1259
import * as path from 'path'
import tailwindcss from '@tailwindcss/vite'
import react from '@vitejs/plugin-react'

// Configuração do SaborSite - Feito pela Ariel!
export default defineConfig({
  plugins: [
    // O motor do React e a beleza do Tailwind
    react(),
    tailwindcss(),
  ],
  resolve: {
    alias: {
      // O atalho mágico '@' para a pasta 'src'
      '@': path.resolve(__dirname, './src'),
    },
  },
  server: {
    // Porta padrão 5173
    port: 5173,
    strictPort: true,
  },
  assetsInclude: ['**/*.svg', '**/*.csv'],
})