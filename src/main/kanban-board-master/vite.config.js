import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [
    react(),
    {
      name: 'ignore-use-client-warnings',
      transform(code, id) {
        if (id.includes('node_modules/@mui/')) {
          return code.replace(/"use client";/g, '');
        }
        return code;
      },
    },
  ],
});