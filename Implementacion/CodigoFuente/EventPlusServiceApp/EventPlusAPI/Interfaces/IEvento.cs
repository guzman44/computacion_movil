using EventPlusAPI.Dao;
using EventPlusAPI.ViewModel;
using System.Collections.Generic;

namespace EventPlusAPI.Interfaces
{
    public interface IEvento
    {
        List<EventoViewModel> GetAll(long idLogin);
        ResponseViewModel Create(EventoViewModel model);
        ResponseViewModel Uptade(EventoViewModel model);
        EventoViewModel GetAllEvento(long idEvento);
        EventoViewModel GetAllGaleriaxEvento(long idEvento);
        EventoViewModel GetAllPublicacionxEvento(long idEvento);
        EventoViewModel GetAllLocalizacionxEvento(long idEvento);
    }
}
