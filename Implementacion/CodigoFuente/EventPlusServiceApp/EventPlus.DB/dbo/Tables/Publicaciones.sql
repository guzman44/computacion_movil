CREATE TABLE [dbo].[Publicaciones] (
    [Id]           INT           NOT NULL,
    [Id_Login]     INT           NOT NULL,
    [Id_Evento]    INT           NOT NULL,
    [Comentario]   VARCHAR (150) NOT NULL,
    [FechaIngreso] DATETIME      NOT NULL,
    [Imagen]       IMAGE         NULL,
    [Activo]       CHAR (1)      DEFAULT ((1)) NOT NULL,
    CONSTRAINT [PK_Publicaciones] PRIMARY KEY CLUSTERED ([Id] ASC)
);


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Tabla que contiene la información de las publicaciones asociadas a una Evento', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Publicaciones';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Id de la tabla', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Publicaciones', @level2type = N'COLUMN', @level2name = N'Id';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Llave Foranea', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Publicaciones', @level2type = N'COLUMN', @level2name = N'Id_Login';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Llave Foranea', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Publicaciones', @level2type = N'COLUMN', @level2name = N'Id_Evento';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Comentario del post', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Publicaciones', @level2type = N'COLUMN', @level2name = N'Comentario';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Fecha cuando s registra el post ', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Publicaciones', @level2type = N'COLUMN', @level2name = N'FechaIngreso';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Imagen de la publicación', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Publicaciones', @level2type = N'COLUMN', @level2name = N'Imagen';

