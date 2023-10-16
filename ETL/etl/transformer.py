from datetime import datetime

class ETLTransformer:
    def __init__(self, column_types):
        self.column_types = column_types

    def convert_to_date(self, value):
        if isinstance(value, datetime):  # Verifica se o valor já é um objeto datetime
            return value
        if isinstance(value, str):  # Verifica se o valor é uma string
            try:
                date_object = datetime.strptime(value, '%Y-%m-%d %H:%M:%S')
                return date_object
            except (ValueError, TypeError):
                return None
        else:
            return None  # Se não for uma string, retorne None

    def transform_data(self, data):
        transformed_data = []

        for row in data:
            transformed_row = []

            for i, value in enumerate(row):
                target_type = self.column_types[i]['type']

                if target_type == 'str':
                    transformed_row.append(str(value))
                elif target_type == 'int':
                    transformed_row.append(int(value))
                elif target_type == 'float':
                    transformed_row.append(float(value))
                elif target_type == 'date':
                    date = self.convert_to_date(value)
                    transformed_row.append(date)

            transformed_data.append(transformed_row)

        return transformed_data
