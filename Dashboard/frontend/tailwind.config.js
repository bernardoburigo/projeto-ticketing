/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}"
  ],
  theme: {
    extend: {
      colors: {
        primary: '#1D4ED8', // azul exemplo
        secondary: '#9333EA', // roxo exemplo
        neutral: '#F3F4F6', // cinza claro
      },
    },
  },
  plugins: [],
}
