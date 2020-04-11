using EventPlusAPI.ViewModel;
using System.Collections.Generic;

namespace EventPlusAPI.Interfaces
{
    public interface ISelect
    {
        List<SelectViewModel> GetbyTipoValor(string tipoValor);
    }
}
