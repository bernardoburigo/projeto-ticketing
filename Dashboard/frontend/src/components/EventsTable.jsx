const events = [
  { id: 1, nome: 'Festival de Verão', data: '10/07/2025', ingressos: 500, receita: 'R$ 25.000' },
  { id: 2, nome: 'Show Rock', data: '20/08/2025', ingressos: 800, receita: 'R$ 48.000' },
  { id: 3, nome: 'Festa Eletrônica', data: '05/09/2025', ingressos: 600, receita: 'R$ 36.000' },
];

const EventsTable = () => {
  return (
    <div className="bg-white p-6 rounded-2xl shadow w-full">
      <h2 className="text-xl font-semibold mb-6 flex items-center gap-2">
        🎉 Próximos Eventos
      </h2>
      <div className="overflow-x-auto">
        <table className="w-full text-left border-separate border-spacing-y-2">
          <thead>
            <tr className="text-gray-600">
              <th className="pb-2 px-4">Evento</th>
              <th className="pb-2 px-4">Data</th>
              <th className="pb-2 px-4">Ingressos</th>
              <th className="pb-2 px-4">Receita</th>
            </tr>
          </thead>
          <tbody>
            {events.map(event => (
              <tr key={event.id} className="bg-gray-50 hover:bg-gray-100 rounded-xl">
                <td className="py-3 px-4">{event.nome}</td>
                <td className="py-3 px-4">{event.data}</td>
                <td className="py-3 px-4">{event.ingressos}</td>
                <td className="py-3 px-4">{event.receita}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default EventsTable;