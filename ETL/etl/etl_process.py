import yaml

from database.connector import DatabaseConnector
from etl.extractor import ETLExtractor
from etl.loader import ETLLoader
from etl.transformer import ETLTransformer


class ETLProcess:
    def __init__(self, config_file):
        with open(config_file, 'r') as file:
            self.config = yaml.load(file, Loader=yaml.FullLoader)

        self.source_connector = DatabaseConnector(self.config['source_database'])
        self.target_connector = DatabaseConnector(self.config['target_database'])

    def run_etl_for_table(self, table_config):
        extractor = ETLExtractor(self.source_connector)
        transformer = ETLTransformer(table_config['column_types'])  # Passar column_types
        loader = ETLLoader(self.target_connector)

        table_name = table_config['name']
        sql_query = table_config['extract_query']
        insert_query = table_config['insert_query']

        if self.source_connector.connect() and self.target_connector.connect():
            data = extractor.extract_data(sql_query)
            transformed_data = transformer.transform_data(data)
            loader.load_data(insert_query, transformed_data)
        else:
            print(f"Falha ao estabelecer conexões para a tabela {table_name}.")
