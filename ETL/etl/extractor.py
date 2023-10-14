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
