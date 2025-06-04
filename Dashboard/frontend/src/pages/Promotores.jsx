import Sidebar from '../components/Sidebar';
import Topbar from '../components/Topbar';
import { FaPlus, FaEdit, FaTrash, FaEye } from 'react-icons/fa';
import { Link } from 'react-router-dom';

const promotores = [
  {
    id: 1,
    nome: 'Agência XYZ',
    email: 'contato@agenciaxyz.com',
    ingressosVendidos: 350,
    receita: 17500,
  },
  {
    id: 2,
    nome: 'Produtora Alpha',
    email: 'alpha@produtora.com',
    ingressosVendidos: 420,
    receita: 21000,
  },
  {
    id: 3,
    nome: 'Eventos Beta',
    email: 'beta@eventos.com',
    ingressosVendidos: 150,
    receita: 7500,
  },
];

const Promotores = () => {
  return (
    <div className="flex">
      <Sidebar />
      <div className="flex-1 flex flex-col">
        <Topbar />
        <main className="p-6 bg-gray-100 min-h-screen">
          <div className="flex justify-between items-center mb-6">
            <h1 className="text-2xl font-bold">Promotores</h1>
            <Link
              to="/promotores/novo"
              className="flex items-center gap-2 bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-xl shadow"
            >
              <FaPlus /> Novo Promotor
            </Link>
          </div>

          <div className="bg-white p-6 rounded-2xl shadow">
            <h2 className="text-xl font-semibold mb-6">👤 Lista de Promotores</h2>
            <div className="overflow-x-auto"></div>
            <table className="w-full table-fixed text-left">
              <thead>
                <tr className="border-b text-gray-600">
                  <th className="py-2">Nome</th>
                  <th className="py-2">E-mail</th>
                  <th className="py-2">Ingressos Vendidos</th>
                  <th className="py-2">Receita (R$)</th>
                  <th className="py-2">Ações</th>
                </tr>
              </thead>
              <tbody>
                {promotores.map((promotor) => (
                  <tr key={promotor.id} className="border-b hover:bg-gray-50">
                    <td className="py-4">{promotor.nome}</td>
                    <td className="py-4">{promotor.email}</td>
                    <td className="py-4">{promotor.ingressosVendidos}</td>
                    <td className="py-4">R$ {promotor.receita.toLocaleString()}</td>
                    <td className="py-4">
                      <div className="flex justify-center gap-4">
                          <Link to={`/promotores/${promotor.id}`} className="text-blue-600 hover:text-blue-800">
                            <FaEye />
                          </Link>
                          <Link to={`/promotores/${promotor.id}/editar`} className="text-yellow-500 hover:text-yellow-600">
                            <FaEdit />
                          </Link>
                          <button className="text-red-600 hover:text-red-800">
                            <FaTrash />
                          </button>
                        </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </main>
      </div>
    </div>
  );
};

export default Promotores;
