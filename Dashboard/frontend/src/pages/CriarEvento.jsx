import Sidebar from '../components/Sidebar';
import Topbar from '../components/Topbar';
import { useNavigate } from 'react-router-dom';

const CriarEvento = () => {
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    // Aqui futuramente vai salvar no backend
    navigate('/eventos'); // Voltar para lista após salvar
  };

  return (
    <div className="flex">
      <Sidebar />
      <div className="flex-1 flex flex-col">
        <Topbar />
        <main className="p-6 bg-gray-100 min-h-screen">
          <h1 className="text-2xl font-bold mb-6">Criar Novo Evento</h1>

          <form
            onSubmit={handleSubmit}
            className="bg-white p-6 rounded-2xl shadow space-y-4 max-w-xl"
          >
            <div>
              <label className="block mb-1">Nome do Evento</label>
              <input
                type="text"
                className="w-full border rounded-xl p-2"
                required
                placeholder="Nome Exemplo"
              />
            </div>

            <div>
              <label className="block mb-1">Data</label>
              <input
                type="date"
                className="w-full border rounded-xl p-2"
                required
              />
            </div>

            <div>
              <label className="block mb-1">Local</label>
              <input
                type="text"
                className="w-full border rounded-xl p-2"
                required
                placeholder="Endereço Exemplo"
              />
            </div>

            <div>
              <label className="block mb-1">Ingressos Disponíveis</label>
              <input
                type="number"
                className="w-full border rounded-xl p-2"
                required
                placeholder="Quantidade Exemplo"
              />
            </div>

            <div className="flex gap-4">
              <button
                type="submit"
                className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-xl"
              >
                Salvar
              </button>
              <button
                type="button"
                onClick={() => navigate('/eventos')}
                className="bg-gray-300 hover:bg-gray-400 px-4 py-2 rounded-xl"
              >
                Cancelar
              </button>
            </div>
          </form>
        </main>
      </div>
    </div>
  );
};

export default CriarEvento;
