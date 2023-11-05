import oracledb
class ETLExtractor:
    def __init__(self, source_connector):
        self.source_connector = source_connector

    def extract_data(self, sql_query):
        try:
            db_connection = self.source_connector.get_connection()
            cursor = db_connection.cursor()
            cursor.execute(sql_query)
            return cursor.fetchall()
        except oracledb.DatabaseError as e:
            print(f"Erro ao extrair dados: {str(e)}")
            return []

    def extract_data_from_dw_table(self, table_name, columns="*", conditions=""):
        try:
            sql_query = f"SELECT {columns} FROM {table_name}"
            if conditions:
                sql_query += f" WHERE {conditions}"

            return self.extract_data(sql_query)
        except Exception as e:
            print(f"Erro ao extrair dados da tabela {table_name}: {str(e)}")
            return []