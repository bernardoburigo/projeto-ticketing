import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api';

const Login = () => {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');
  const [erro, setErro] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setErro('');

    try {
      const response = await api.post('/usuarios/login', {
        email,
        senha,
      });

      const token = response.data.token;
      localStorage.setItem('token', token);
      navigate('/dashboard');
    } catch (err) {
      setErro('Credenciais inválidas. Tente novamente.');
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <form onSubmit={handleLogin} className="bg-white p-8 rounded shadow-md w-full max-w-md">
        <h1 className="text-2xl font-bold text-center mb-6 text-blue-600">Login</h1>

        <label className="block mb-2">Email</label>
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          className="w-full px-4 py-2 border rounded mb-4"
        />

        <label className="block mb-2">Senha</label>
        <input
          type="password"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
          required
          className="w-full px-4 py-2 border rounded mb-4"
        />

        {erro && <p className="text-red-500 text-center mb-4">{erro}</p>}

        <button type="submit" className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700">
          Entrar
        </button>
      </form>
    </div>
  );
};

export default Login;
