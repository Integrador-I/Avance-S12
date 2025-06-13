"use client"
import React, { useState } from 'react';
import { useRouter } from 'next/navigation';

const correo = 'admin@gmail.com';
const contraseña = 'admin123';

const LoginForm: React.FC = () => {
  const router = useRouter();

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const copyToClipboard = async (text: string) => {
    try {
      await navigator.clipboard.writeText(text);
    } catch {
    }
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    setError('');

    if (!email) {
      setError('Por favor ingrese su correo electrónico.');
      return;
    }
    if (!password) {
      setError('Por favor ingrese su contraseña.');
      return;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
      setError('Correo electrónico inválido.');
      return;
    }

    if (email === correo && password === contraseña) {
      router.push('/dashboard');
    } else {
      setError('Credenciales incorrectas. Por favor, intente nuevamente.');
    }
  };

  return (
    <form onSubmit={handleSubmit} className="bg-white p-10 rounded-3xl shadow-xl max-w-md w-full">
      <h2 className="text-3xl font-extrabold mb-8 text-gray-900">Iniciar Sesión</h2>
      {error && (
        <div
          role="alert"
          className="mb-6 text-red-700 bg-red-100 border border-red-400 px-5 py-4 rounded-lg text-center font-semibold"
        >
          {error}
        </div>
      )}

      <div className="mb-6">
        <label htmlFor="email" className="block text-gray-700 font-semibold mb-2">
          Correo Electrónico
        </label>
        <input
          id="email"
          type="email"
          autoComplete="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder="correo@ejemplo.com"
          className="w-full border border-gray-300 rounded-xl px-5 py-3 text-lg focus:outline-none focus:ring-4 focus:ring-indigo-500 transition"
          aria-label="Correo Electrónico"
        />
      </div>

      <div className="mb-8">
        <label htmlFor="password" className="block text-gray-700 font-semibold mb-2">
          Contraseña
        </label>
        <input
          id="password"
          type="password"
          autoComplete="current-password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder=""
          className="w-full border border-gray-300 rounded-xl px-5 py-3 text-lg focus:outline-none focus:ring-4 focus:ring-indigo-500 transition"
          aria-label="Contraseña"
        />
      </div>

      <button
        type="submit"
        className="w-full bg-indigo-600 text-white font-bold py-4 rounded-xl shadow-lg hover:bg-indigo-700 transition text-lg focus:ring-4 focus:ring-indigo-400 focus:outline-none"
      >
        Iniciar
      </button>

      <div
        className="mt-8 p-4 border border-gray-300 rounded-xl bg-gray-50 text-gray-700 font-medium select-none"
        aria-label="Cuenta demo de administrador"
      >
        <div className="mb-3 text-indigo-600 font-semibold">Demo Admin Account</div>
        <div className="flex flex-col gap-2">
          <div className="flex items-center justify-between">
            <span>Email:</span>
            <button
              type="button"
              onClick={() => copyToClipboard(correo)}
              className="text-indigo-600 hover:underline font-mono cursor-pointer select-text"
              aria-label="Copiar correo electrónico demo"
            >
              {correo}
            </button>
          </div>
          <div className="flex items-center justify-between">
            <span>Password:</span>
            <button
              type="button"
              onClick={() => copyToClipboard(contraseña)}
              className="text-indigo-600 hover:underline font-mono cursor-pointer select-text"
              aria-label="Copiar contraseña demo"
            >
              {contraseña}
            </button>
          </div>
        </div>
      </div>
    </form>
  );
};

export default LoginForm;