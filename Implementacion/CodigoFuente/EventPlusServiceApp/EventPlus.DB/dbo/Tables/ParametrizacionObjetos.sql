CREATE TABLE [dbo].[ParametrizacionObjetos] (
    [Id]     INT           NOT NULL,
    [Nombre] VARCHAR (50)  NULL,
    [Valor]  VARCHAR (100) NOT NULL,
    [Activo] CHAR (1)      DEFAULT ((1)) NOT NULL,
    CONSTRAINT [PK_ParametrizacionObjetos] PRIMARY KEY CLUSTERED ([Id] ASC)
);


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Parametrizacion de los objetos', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'ParametrizacionObjetos';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Id de la tabla', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'ParametrizacionObjetos', @level2type = N'COLUMN', @level2name = N'Id';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'nombre de la categoria', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'ParametrizacionObjetos', @level2type = N'COLUMN', @level2name = N'Nombre';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Valores que va contener la categoria', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'ParametrizacionObjetos', @level2type = N'COLUMN', @level2name = N'Valor';


GO
EXECUTE sp_addextendedproperty @name = N'MS_Description', @value = 'Activo en la lista', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'TABLE', @level1name = N'ParametrizacionObjetos', @level2type = N'COLUMN', @level2name = N'Activo';

