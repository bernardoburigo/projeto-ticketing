import Sidebar from '../components/Sidebar';
import Topbar from '../components/Topbar';
import { FaPlus, FaEdit, FaTrash, FaEye } from 'react-icons/fa';
import { Link } from 'react-router-dom';

const eventos = [
  { id: 1, nome: 'Festival de Verão', data: '10/07/2025', local: 'Praça Central', ingressos: 500, receita: 'R$ 25.000' },
  { id: 2, nome: 'Show Rock', data: '20/08/2025', local: 'Estádio Municipal', ingressos: 800, receita: 'R$ 48.000' },
  { id: 3, nome: 'Festa Eletrônica', data: '05/09/2025', local: 'Clube Noite Branca', ingressos: 600, receita: 'R$ 36.000' },
];

const Eventos = () => {
  return (
    <div className="flex">
      <Sidebar />
      <div className="flex-1 flex flex-col">
        <Topbar />
        <main className="p-6 bg-gray-100 min-h-screen">
          <div className="flex justify-between items-center mb-6">
            <h1 className="text-2xl font-bold">Eventos</h1>
            <Link to='/eventos/novo' className="flex items-center gap-2 bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-xl shadow">
              <FaPlus />
              Novo Evento
            </Link>
          </div>

          <div className="bg-white p-6 rounded-2xl shadow">
            <h2 className="text-xl font-semibold mb-6">📅 Lista de Eventos</h2>
            <div className="overflow-x-auto">
              <table className="w-full table-fixed text-left">
                <thead>
                  <tr className="border-b text-gray-600">
                    <th className="pb-4 w-2/6">Evento</th>
                    <th className="pb-4 w-1/6">Data</th>
                    <th className="pb-4 w-1/6">Local</th>
                    <th className="pb-4 w-1/6">Ingressos</th>
                    <th className="pb-4 w-1/6">Receita</th>
                    <th className="pb-4 w-1/6 text-center">Ações</th>
                  </tr>
                </thead>
                <tbody>
                  {eventos.map(evento => (
                    <tr key={evento.id} className="border-b hover:bg-gray-50">
                      <td className="py-4">{evento.nome}</td>
                      <td className="py-4">{evento.data}</td>
                      <td className="py-4">{evento.local}</td>
                      <td className="py-4">{evento.ingressos}</td>
                      <td className="py-4">{evento.receita}</td>
                      <td className="py-4">
                        <div className="flex justify-center gap-4">
                          <Link to={`/eventos/${evento.id}`} className="text-blue-600 hover:text-blue-800">
                            <FaEye />
                          </Link>
                          <Link to={`/eventos/${evento.id}/editar`} className="text-yellow-500 hover:text-yellow-600">
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
          </div>
        </main>
      </div>
    </div>
  );
};

export default Eventos;
