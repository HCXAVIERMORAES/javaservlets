package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelTelefone;

public class DAOTelefoneRepository {

	private Connection connection;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	public DAOTelefoneRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	//lista de retorno dos telefones
	public List<ModelTelefone> listFone(Long idUserPai) throws Exception {
		
		List<ModelTelefone> retorno = new ArrayList<ModelTelefone>();
		
		String sql = "SELECT * from telefone where usuario_pai_id = ?;";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setLong(1, idUserPai);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			ModelTelefone modelTelefone = new ModelTelefone();
			
			modelTelefone.setId(rs.getLong("id"));
			modelTelefone.setNumero(rs.getString("numero"));
			modelTelefone.setUsuario_cad_id(daoUsuarioRepository.consultaUsuarioID(rs.getLong("usuario_cad_id")));
			modelTelefone.setUsuario_pai_id(daoUsuarioRepository.consultaUsuarioID(rs.getLong("usuario_pai_id")));
			
			retorno.add(modelTelefone);			
		}
		
		return retorno;		
	}
	
	//metodo para gravar o telefone
	public void gravaTelefone(ModelTelefone modelTelefone) throws Exception{
		
		String sql = "INSERT INTO telefone (numero, usuario_pai_id, usuario_cad_id) values (?,?,?)";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setString(1, modelTelefone.getNumero());
		preparedStatement.setLong(2, modelTelefone.getUsuario_pai_id().getId());
		preparedStatement.setLong(3, modelTelefone.getUsuario_cad_id().getId());
		
		preparedStatement.execute();
		connection.commit();
	}
	
    //metodo para deletar o telefone
	public void deleteFone(Long id) throws Exception {
		
		String sql = "DELETE from telefone where id = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, id);
		preparedStatement.executeUpdate();
		
		connection.commit();
	}
	
	//evitar telefones duplicados
public boolean existeFone(String fone, Long idUse) throws Exception {
		
		String sql = "select count(1) > 0 as existe from telefone where usuario_pai_id =? and numero = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, idUse);
		preparedStatement.setString(2, fone);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		resultSet.next();
		
		return resultSet.getBoolean("existe");
	}
	
}
