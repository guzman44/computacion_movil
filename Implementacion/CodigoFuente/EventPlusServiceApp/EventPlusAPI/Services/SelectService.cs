using EventPlusAPI.Dao;
using EventPlusAPI.ViewModel;
using EventPlusAPI.Interfaces;
using System.Collections.Generic;
using System.Linq;

namespace EventPlusAPI.Services
{
    public class SelectService : ISelect
    {
        public readonly EventPlusContext _eventPlusContext;

        public SelectService(EventPlusContext _eventPlusContext)
        {
            this._eventPlusContext = _eventPlusContext;
        }


        public List<SelectViewModel> GetbyTipoValor(string tipoValor)
        {
            return _eventPlusContext.ParametrizacionObjetos.Where(s => s.Nombre == tipoValor && s.Activo == "1").Select(s => new SelectViewModel
            {
                Id = s.Id,
                Valor = s.Valor
            }).ToList();
        }
    }
}
