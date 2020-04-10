using EventPlusAPI.Dao;
using EventPlusAPI.ViewModel;
using System.Collections.Generic;

namespace EventPlusAPI.Interfaces
{
    public interface IEvento
    {
        List<AllHistoryEventoViewModel> GetAll(long idLogin);
        ResponseViewModel Create(CreateEventoViewModel model);
        ResponseViewModel Uptade(UpdateEventoViewModel model);
        AllEventoViewModel GetAllEvento(long idEvento);
        GalleryEventoViewModel GetAllGaleriaxEvento(long idEvento);
        PublicationEventoViewModel GetAllPublicacionxEvento(long idEvento);
        LocationEventoViewModel GetAllLocalizacionxEvento(long idEvento);
        ResponseViewModel CreateGallery(CreateGalleryViewModel model);
        ResponseViewModel CreateGalleryAll(List<CreateGalleryViewModel> model);
        ResponseViewModel CreatePublication(CreatePublicationViewModel model);
        ResponseViewModel CreateLocation(CreateLocationViewModel model);
        ResponseViewModel CreateLocationAll(List<CreateLocationViewModel> model);
    }
}
