import Sidebar from '../components/Sidebar';
import Topbar from '../components/Topbar';
import { useNavigate, useParams } from 'react-router-dom';

const EditarPromotor = () => {
  const navigate = useNavigate();
  const { id } = useParams();

  const handleSubmit = (e) => {
    e.preventDefault();
    // Atualizar futuramente no backend
    navigate('/promotores');
  };

  return (
    <div className="flex">
      <Sidebar />
      <div className="flex-1 flex flex-col">
        <Topbar />
        <main className="p-6 bg-gray-100 min-h-screen">
          <h1 className="text-2xl font-bold mb-6">Editar Promotor #{id}</h1>

          <form
            onSubmit={handleSubmit}
            className="bg-white p-6 rounded-2xl shadow space-y-4 max-w-xl"
          >
            <div>
              <label className="block mb-1">Nome do Promotor</label>
              <input
                type="text"
                className="w-full border rounded-xl p-2"
                required
                defaultValue="Victor Pereira"
              />
            </div>

            <div>
              <label className="block mb-1">Código</label>
              <input
                type="text"
                className="w-full border rounded-xl p-2"
                required
                defaultValue="victorp"
              />
            </div>

            <div>
              <label className="block mb-1">Email</label>
              <input
                type="email"
                className="w-full border rounded-xl p-2"
                required
                defaultValue="contato@victorp.com.br"
              />
            </div>

            <div>
              <label className="block mb-1">Telefone</label>
              <input
                type="tel"
                className="w-full border rounded-xl p-2"
                required
                defaultValue="(48) 98765-4321"
              />
            </div>

            <div>
              <label className="block mb-1">Evento Relacionado</label>
              <select
                className="w-full border rounded-xl p-2"
                required
                defaultValue="Festival de Música"
              >
                <option value="">Selecione um evento</option>
                <option value="Festival de Música">Festival de Música</option>
                <option value="Congresso Tech">Congresso Tech</option>
                <option value="Workshop de Design">Workshop de Design</option>
              </select>
            </div>

            <div className="flex gap-4">
              <button
                type="submit"
                className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-xl"
              >
                Salvar
              </button>
              <button
                type="button"
                onClick={() => navigate('/promotores')}
                className="bg-gray-300 hover:bg-gray-400 text-black px-4 py-2 rounded-xl"
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

export default EditarPromotor;
