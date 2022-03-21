package com.grpc.model;

import com.company.keystore.crypto.KeyPair;
import com.company.keystore.wallet.WalletUtility;
import com.google.common.collect.ImmutableList;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Utils;
import org.bitcoinj.crypto.*;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bitcoinj.wallet.Wallet;
import org.spongycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
//import sun.security.provider.SecureRandom;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ZJC {

    private final static ImmutableList<ChildNumber> BIP44_ETH_ACCOUNT_ZERO_PATH =
            ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true),
                    ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

//    private static void createWallet()  throws MnemonicException.MnemonicLengthException {
//        SecureRandom secureRandom = new SecureRandom();
//        byte[] entropy = new byte[DeterministicSeed.DEFAULT_SEED_ENTROPY_BITS / 8];
//        secureRandom.engineNextBytes(entropy);
//
//        //生成12位助记词
//        List<String> str = MnemonicCode.INSTANCE.toMnemonic(entropy);
//
//        //使用助记词生成钱包种子
//        byte[] seed = MnemonicCode.toSeed(str, "");
//        DeterministicKey masterPrivateKey = HDKeyDerivation.createMasterPrivateKey(seed);
//        DeterministicHierarchy deterministicHierarchy = new DeterministicHierarchy(masterPrivateKey);
//        DeterministicKey deterministicKey = deterministicHierarchy
//                .deriveChild(BIP44_ETH_ACCOUNT_ZERO_PATH, false, true, new ChildNumber(0));
//        byte[] bytes = deterministicKey.getPrivKeyBytes();
//        System.out.println("pirvate: "+ Hex.toHexString(bytes)+"---------->"+bytes.length);
//        String publickey= WalletUtility.prikeyToPubkey(Hex.toHexString(bytes));
//
//        KeyPair keyPair = new KeyPair(Hex.decode(publickey.getBytes()),bytes);
//        System.out.println("onlyPirvate: "+Hex.toHexString(keyPair.getPrivateKey().getBytes())+"-----> "+keyPair.getPrivateKey().getBytes().length);
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!=>"+WalletUtility.pubkeyStrToPubkeyHashStr(publickey));
//        String address=WalletUtility.pubkeyHashToAddress(WalletUtility.pubkeyStrToPubkeyHashStr(publickey),0);
//
//        //ETH
////        ECKeyPair keyPair = ECKeyPair.create(bytes);
////        //通过公钥生成钱包地址
////        String address = Keys.getAddress(keyPair.getPublicKey());
//
//
//        System.out.println("是否正确："+WalletUtility.verifyAddress(address));
//        System.out.println();
//        System.out.println("助记词：");
//        System.out.println(str);
//        System.out.println();
//        System.out.println("地址：");
//        System.out.println(""+address);
//        System.out.println();
//        System.out.println("私钥：");
////        System.out.println("0x"+keyPair.getPrivateKey().toString(16));
//        System.out.println();
//        System.out.println("公钥：");
////        System.out.println(keyPair.getPublicKey().toString(16));
//    }


    public static void main(String[] args) throws Exception {
        //创建钱包
//        createWallet();
        test1();
        System.out.println(Hex.decode("3a79874eadaa3915b8f69339a96fb575062cae36fd59527f3134b93d3960f59aa02f30de855cdbc5d4167c86ca72288a28b6dfb8bab09c23137df87aa625b027").length);


        KeyPair keyPair = KeyPair.generateEd25519KeyPair();
        SecureRandom random = new SecureRandom();
        random.nextBytes(keyPair.getPrivateKey().getBytes());
        MnemonicCode mnemonicCode = new MnemonicCode();
        List<String> mnemoniclist = mnemonicCode.toMnemonic(keyPair.getPrivateKey().getBytes());
        System.out.println(mnemoniclist.toString());


    }

    public static void test1(){
        NetworkParameters networkParameters = MainNetParams.get() ;
        DeterministicSeed seed = new DeterministicSeed(new SecureRandom(), 128, "", Utils.currentTimeSeconds());
        Wallet wallet;
        String mnemonics = "";
        String privateKey = "";
        String publicKey = "";
        String address = "";
        String pwd = "";
        try {
            wallet = Wallet.fromSeed(networkParameters, seed);
            //私钥
            privateKey = wallet.currentReceiveKey().getPrivateKeyAsWiF(networkParameters);
            //助记词
            mnemonics = wallet.getKeyChainSeed().getMnemonicCode().toString();
            publicKey = Hex.toHexString(ECKey.publicKeyFromPrivate(wallet.currentReceiveKey().getPrivKey(), true));
            //地址
            address = wallet.currentReceiveAddress().toBase58();
        } catch (Exception e) {
            System.out.println("【比特币钱包创建】失败，原因"+e);
            return ;
        }
        System.out.println("mnemonics："+mnemonics);
        System.out.println("privateKey："+privateKey+"------>"+privateKey.getBytes(StandardCharsets.UTF_8).length);
        System.out.println("publicKey："+publicKey);
        System.out.println("address："+address);
    }
}
