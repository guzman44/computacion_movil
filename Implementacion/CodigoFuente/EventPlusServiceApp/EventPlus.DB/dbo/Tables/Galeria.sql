CREATE TABLE [dbo].[Galeria] (
    [Id]            INT      IDENTITY (1, 1) NOT NULL,
    [Image]         IMAGE    NOT NULL,
    [IdEvento]      INT      NOT NULL,
    [FechaRegistro] DATETIME NOT NULL,
    CONSTRAINT [PK_Galleria] PRIMARY KEY CLUSTERED ([Id] ASC),
    CONSTRAINT [FK_Galeria_Evento] FOREIGN KEY ([IdEvento]) REFERENCES [dbo].[Evento] ([Id]),
    CONSTRAINT [UQ_Galleria_Id] UNIQUE NONCLUSTERED ([Id] ASC)
);


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Fecha Registro', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Galeria', @level2type = N'COLUMN', @level2name = N'FechaRegistro';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Llave Foranea', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Galeria', @level2type = N'COLUMN', @level2name = N'IdEvento';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Imagen de la galeria', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Galeria', @level2type = N'COLUMN', @level2name = N'Image';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Id de la tabla', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Galeria', @level2type = N'COLUMN', @level2name = N'Id';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Tabla que contiene la información de las imagenes de los eventos - Galleria', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'Galeria';

